# simple script to replace dandelion.war in tomcat 7
/etc/init.d/tomcat7 stop
rm -rf /var/lib/tomcat7/webapps/Dandelion*
cp /home/cinnamon/Dandelion.war.new /var/lib/tomcat7/webapps/Dandelion.war
/etc/init.d/tomcat7 start
