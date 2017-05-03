@ECHO OFF
echo Main Starter
cd registrationservice
echo Starting registration service...
start mvn spring-boot:run
cd ..
echo Starting asterisk service...
cd asteriskservice
start mvn spring-boot:start
cd ..
echo Starting configuration service...
cd configurationservice
start mvn spring-boot:start
cd ..
echo Starting persistence service...
cd persistenceservice
start mvn spring-boot:start
cd ..

