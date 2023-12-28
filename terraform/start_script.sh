#!/bin/bash

export DEBIAN_FRONTEND=noninteractive
sudo apt update
sudo DEBIAN_FRONTEND=noninteractive apt upgrade -y

sudo DEBIAN_FRONTEND=noninteractive apt install apache2 -y

sudo reboot