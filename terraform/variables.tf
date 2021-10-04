variable region {
    default = "eu-west-3"
}
variable vpc_cidr_block {
    default = "10.0.0.0/16"
}
variable subnet_cidr_block {
    default = "10.0.10.0/24"
}
variable avail_zone {
    default = "eu-west-3a"
}
variable env_prefix {
    default = "dev"
}
variable my_ip {
    // Who is allowed to ssh into the ec2 instance (I'll allow any one for now)
    default = "0.0.0.0/0"
}
variable instance_type {
    default = "t2.micro"
}