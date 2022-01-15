#!/bin/bash
mvn install:install-file -Dfile='./lib/bukkit-resources-0.0.1-SNAPSHOT.jar' -DgroupId='org.bukkit' -DartifactId='bukkit-resources' -Dversion='0.0.1-SNAPSHOT' -Dpackaging=jar -DlocalRepositoryPath='./lib'
