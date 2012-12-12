Dandelion: an administrative tool for the Cinnamon CMS.

License: LGPL 2.1 - http://www.gnu.org/licenses/lgpl-2.1.html

Dependencies: see file 'dependencies.txt'

Dandelion uses images from the Crystal Project 
(Website: http://www.everaldo.com/crystal/ License: LGPL; author: Everaldo Coelho)

To build:
1. Use Server/build.example.xml to build internal dependencies (utils, entitylib, safran)
2. use "grails war" to generate target/dandelion.war

To install:
- adjust dandelion-config.example.groovy
- rename to dandelion-config.groovy
- set environment variable DANDELION_HOME_DIR to the path where
    the config file is to be found. (You may have to restart your system).
- copy dandelion.war into your web container (Tomcat / Jetty).

