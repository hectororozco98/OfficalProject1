# The first line is always FROM that defines a base image
FROM tomcat:8.0-jre8

# Adding info about who created this image
LABEL maintainer="Hector"

# We need to move the war file to teh webapps directory
ADD target/official-project-one.war /usr/local/tomcat/webapps
# the webapps directory containts the app that tomcat serves

# Expose port 8080 from the container
EXPOSE 8080

# CMD instruction specifies what to run when the container is run
# In our case the tomcat server is started by running a shell script
CMD ["catalina.sh", "run"]