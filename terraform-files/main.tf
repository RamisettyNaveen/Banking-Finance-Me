resource "aws_instance" "New-test-server" {
  ami           = "ami-09298640a92b2d12c" 
  instance_type = "t2.micro" 
  key_name = "insureme"
  vpc_security_group_ids= ["sg-083b782d2bba51a47"]
  connection {
    type     = "ssh"
    user     = "ec2-user"
    private_key = file("./insureme.pem")
    host     = self.public_ip
  }
  provisioner "remote-exec" {
    inline = [ "echo 'wait to start instance' "]
  }
  tags = {
    Name = "New-test-server"
  }
  provisioner "local-exec" {
    command = "echo ${aws_instance.New-test-server.public_ip} > inventory"
  }
  provisioner "local-exec" {
    command = "ansible-playbook /var/lib/jenkins/workspace/Banking-Finance-Me/terraform-files/ansibleplaybook.yml"
  }
  }
