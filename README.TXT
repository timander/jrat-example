###############################################################################
The point of this project is to demonstrate
how to use JRat to find bottlenecks in code.
XML Parsing seemed to me like a good domain
to use for analyzing performance.
###############################################################################
Code Examples for XML Parsing are from
http://www.totheriver.com/learn/xml/xmltutorial.html
###############################################################################


###########################################
Install JRat to your local maven repository
-------------------------------------------
mvn install:install-file -Dfile=shiftone-jrat-1-alpha2.jar -DgroupId=org.shiftone.jrat -DartifactId=shiftone-jrat -Dversion=1-alpha2 -Dpackaging=jar -DgeneratePom=true -DcreateChecksum=true

###########################################
Open JRat GUI
-------------------------------------------
java -jar shiftone-jrat-1-alpha2.jar
	#instrument target/classes directory

###########################################
Execute code with JRat
-------------------------------------------
mvn exec:java -Dexec.mainClass="net.timandersen.App" -Dexec.classpathScope=test -Djrat.timing.nanoseconds=true -Djrat.factory="org.shiftone.jrat.provider.tree.TreeMethodHandlerFactory"

###########################################
JRat documentation for alternative handlers
-------------------------------------------
http://wiki.shiftone.org/index.php?title=JRat_Handlers


###########################################
Alternative ways to run JRat is to build
executable jar and include JRat at runtime
-------------------------------------------
    mvn package
    java -javaagent:shiftone-jrat-1-alpha2.jar -Djrat.timing.nanoseconds=true -Djrat.factory="org.shiftone.jrat.provider.tree.TreeMethodHandlerFactory" -jar target/jrat-examples.jar
