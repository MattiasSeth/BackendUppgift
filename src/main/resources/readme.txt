######### START DATABASE #########

# Skapa schema i databasen med följande script
CREATE SCHEMA `backenduppgift`;

# Skapa databas användare med följande script
CREATE USER 'booking'@'localhost' IDENTIFIED BY 'secret';

# Ge anvädare full access till databas-schemat
GRANT ALL PRIVILEGES ON backenduppgift.* TO 'booking'@'localhost' WITH GRANT OPTION;

######### END DATABASE #########