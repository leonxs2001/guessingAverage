resource "aws_db_subnet_group" "db_subnet_group" {
  name       = "tf-db_subnet_group"
  subnet_ids = [for i in range(var.num_private_subnets) : aws_subnet.private_subnet[i].id]
}

resource "aws_db_instance" "postgres_instance" {
  identifier            = "tf-p-postgres-db"
  allocated_storage     = 20
  engine                = "postgres"
  instance_class        = "db.t3.micro"
  db_name               = "mydatabase"
  username              = "postgres"
  password              = var.credentials["db_password"]
  db_subnet_group_name  = aws_db_subnet_group.db_subnet_group.name
  vpc_security_group_ids = [aws_security_group.postgres_sg.id]
  multi_az              = true
  skip_final_snapshot   = true
}
