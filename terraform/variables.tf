variable "aws_region" {
 description = "Region to use for AWS resources"
 type = string
 default = "us-east-1"
 sensitive = true
}

variable "num_public_subnets" {
 description = "Number of public subnets in VPC"
 type = number
 default = 2
}

variable "num_private_subnets" {
 description = "Number of private subnets in VPC"
 type = number
 default = 2
}

variable "credentials" {
 type = map(string)  # keys: "access_key", "secret_key", "token", "db_password"
}