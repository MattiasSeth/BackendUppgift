######### START DATABASE #########

# Skapa schema i databasen med följande script
CREATE SCHEMA `backenddatabase`;

# Skapa databas användare med följande script
CREATE USER 'seth2'@'localhost' IDENTIFIED BY 'secretPassword';

# Ge anvädare full access till databas-schemat
grant all on *.* to 'seth2'@'localhost' with grant option;

######### END DATABASE #########