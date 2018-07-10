sudo apt update
sudo apt install mysql-server mysql-common mysql-client -y

mysql -u root -p < script_create.sql
