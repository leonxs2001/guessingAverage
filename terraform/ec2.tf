data "template_file" "user_data" {
  template = file("${path.module}/start_script.sh")
}

resource "aws_launch_template" "webserver-lt" {
  name          = "tf-webserver-lt"

  image_id      = "ami-0c7217cdde317cfec"

  instance_type = "t2.micro"

  vpc_security_group_ids = [aws_security_group.webserver_w_lb.id]
  key_name = "IaC_20231006"
  user_data = base64encode(data.template_file.user_data.rendered)
}

resource "aws_autoscaling_group" "webserver-asg" {
  name                 = "tf-webserver-asg"
  desired_capacity     = 2
  max_size             = 2
  min_size             = 1
  vpc_zone_identifier  = [for i in range(var.num_public_subnets) : aws_subnet.public_subnet[i].id]

  launch_template {
    id      = aws_launch_template.webserver-lt.id
    version = "$Latest"
  }
  target_group_arns = [aws_lb_target_group.webserver-tg.arn]
}