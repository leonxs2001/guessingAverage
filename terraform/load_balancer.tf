resource "aws_lb_target_group" "webserver-tg" {
  name        = "tf-webserver-tg"
  port        = 80
  protocol    = "HTTP"
  target_type = "instance"
  vpc_id      = aws_vpc.webserver_vpc.id
}

resource "aws_lb" "webserver-alb" {
  name               = "tf-webserver-alb"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.webserver_wo_ssh.id]
  subnets            = [for i in range(var.num_public_subnets) : aws_subnet.public_subnet[i].id]
}

resource "aws_lb_listener" "webserver-listener" {
  load_balancer_arn = aws_lb.webserver-alb.arn
  port              = 80
  protocol          = "HTTP"

  default_action {
    type = "forward"
    target_group_arn = aws_lb_target_group.webserver-tg.arn
  }
}
