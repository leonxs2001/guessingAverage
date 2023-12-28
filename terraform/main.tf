provider "aws" {
 region = var.aws_region
 access_key = var.credentials["access_key"]
 secret_key = var.credentials["secret_key"]
 token = var.credentials["token"]
}
