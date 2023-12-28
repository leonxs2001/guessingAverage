data "aws_availability_zones" "available" {}

# VPC
resource "aws_vpc" "webserver_vpc" {
  cidr_block = "10.0.0.0/16"
  enable_dns_support = true
  enable_dns_hostnames = true

  tags = {
    Name = "tf-vpc"
  }
}

# S3 Endpoint
resource "aws_vpc_endpoint" "s3_endpoint" {
  vpc_id = aws_vpc.webserver_vpc.id

  service_name = "com.amazonaws.us-east-1.s3"
}

# subnets
resource "aws_subnet" "public_subnet" {
  count = var.num_public_subnets

  vpc_id                  = aws_vpc.webserver_vpc.id
  cidr_block              = format("10.0.%d.0/24", count.index)
  availability_zone       = element(data.aws_availability_zones.available.names, count.index)
  map_public_ip_on_launch = true

  tags = {
    Name = "public-subnet-${count.index + 1}"
  }
}

resource "aws_subnet" "private_subnet" {
  count = var.num_private_subnets

  vpc_id                  = aws_vpc.webserver_vpc.id
  cidr_block              = format("10.0.%d.0/24", var.num_public_subnets + count.index)
  availability_zone       = element(data.aws_availability_zones.available.names, count.index)
  map_public_ip_on_launch = false

  tags = {
    Name = "private-subnet-${count.index + 1}"
  }
}

# internet gateway
resource "aws_internet_gateway" "public_ig" {
  vpc_id = aws_vpc.webserver_vpc.id
}

# routing tables
resource "aws_route_table" "public_route_table" {
  vpc_id = aws_vpc.webserver_vpc.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.public_ig.id
  }
  route {
    cidr_block = aws_vpc.webserver_vpc.cidr_block
    gateway_id = "local"
  }
}

resource "aws_route_table" "private_route_table" {
  vpc_id = aws_vpc.webserver_vpc.id
  route {
    cidr_block = aws_vpc.webserver_vpc.cidr_block
    gateway_id = "local"
  }
}
resource "aws_vpc_endpoint_route_table_association" "s3_endpoint_association" {
  route_table_id  = aws_route_table.private_route_table.id
  vpc_endpoint_id = aws_vpc_endpoint.s3_endpoint.id
}
resource "aws_route_table_association" "private_association" {
  count = var.num_private_subnets
  subnet_id      = aws_subnet.private_subnet[count.index].id
  route_table_id = aws_route_table.private_route_table.id
}
resource "aws_route_table_association" "public_association" {
  count = var.num_public_subnets
  subnet_id      = aws_subnet.public_subnet[count.index].id
  route_table_id = aws_route_table.public_route_table.id
}