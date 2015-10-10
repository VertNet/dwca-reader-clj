(defproject dwca-reader2-clj/dwca-reader-clj "0.20-SNAPSHOT" 
  :description "Clojure wrapper for the GBIF Darwin Core Archive Reader library version 1.20."
  :url "https://github.com/VertNet/dwca-reader-clj"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/license/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.gbif/dwca-reader "1.21"]
                 [net.lingala.zip4j/zip4j "1.3.1"]
                 [com.google.guava/guava "12.0"]
                 [clj-http "0.5.7"]
                 [org.gbif/gbif-common "0.16"]]
  :scm {:name "git"
        :url "https://github.com/VertNet/dwca-reader-clj"}
  :source-paths ["src/clj"]
  :profiles {:dev
             {:resource-paths
              ["dev"],
              :dependencies [[midje "1.4.0"]]
              :plugins [[lein-swank "1.4.4"]
                        [lein-midje "1.0.8"]
                        [lein-clojars "0.9.0"]]}}
  :repositories {"conjars" "http://conjars.org/repo/",
                 "gbif"
                 "http://repository.gbif.org/content/groups/gbif/",
                 "zip4j" "http://mvnrepository.com/artifact/",
                 "maven2" "http://repo2.maven.org/maven2"}
  :min-lein-version "2.0.0"
  :jvm-opts ["-XX:MaxPermSize=128M"
             "-XX:+UseConcMarkSweepGC"
             "-Xms1024M"
             "-Xmx1048M"
             "-server"]
  :deploy-repositories [["clojars" {:creds :gpg}]]
  :signing {:gpg-key "gtuco.btuco@gmail.com"}
)