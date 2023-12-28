resource "aws_security_group" "webserver_wo_ssh" {
  name        = "tf-webserver_wo_ssh"
  description = "Security Group for HTTP without SSH"
  vpc_id = aws_vpc.webserver_vpc.id
  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_security_group" "webserver_w_lb" {
  name        = "tf-webserver_ssh"
  description = "Security Group for HTTP over load balancer and SSH from THB network"
  vpc_id = aws_vpc.webserver_vpc.id
  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    #cidr_blocks = ["0.0.0.0/0"]
    security_groups = [aws_security_group.webserver_wo_ssh.id]
  }
  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["195.37.2.0/24"]
  }
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_security_group" "postgres_sg" {
  name        = "tf-postgres-from-sg"
  description = "Security group for PostgreSQL database to access from ec2"
  vpc_id = aws_vpc.webserver_vpc.id
  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    security_groups = [aws_security_group.webserver_w_lb.id]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}