FROM tomcat:9.0-jdk11-temurin

COPY EmployeeManagementSystem.war/usr/local/tomcat/webapps/ROOT.war

COPY start.sh/start.sh
RUN chmod_x/start.sh
EXPOSE 8080
CMD["/start.sh"]
