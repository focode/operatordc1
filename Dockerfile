# Use the specified Apache Spark base image
FROM apache/spark:3.5.1-scala2.12-java17-python3-r-ubuntu

# Copy Spark application jar to the appropriate directories
COPY operatordc1-0.0.1-SNAPSHOT.jar /opt/spark/examples/jars/

# Remove existing Gson JARs from /opt/spark/jars/ and add the desired Gson version
RUN find /opt/spark/jars/ -name 'gson*.jar' -delete
COPY gson-2.9.1.jar /opt/spark/jars/

# Create the csv and par directories under /opt/spark/examples/jars/
RUN mkdir -p /opt/spark/examples/jars/csv
RUN mkdir -p /opt/spark/examples/jars/par

# Copy the CSV file into the newly created csv directory
COPY nfrschema1.csv /opt/spark/examples/jars/csv/
