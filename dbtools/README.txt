DBUnit export and import tools.
to run this tools, please follow the next step:
1, checkout the source
2, run: mvn eclipse:eclipse
3, change configuration src/main/resources/jdbc.properties
4, run
   ExportSchema: export all schema
   ExportTable: export the given tables into xml
   ImportTable: import xml into database
