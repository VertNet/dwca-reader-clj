(ns dwca.core-test
  (:use dwca.core)
  (:use [midje.sweet])
  (:require [clojure.java.io :as io])
  (:import [java.io File]
           [com.google.common.io Files]))

(fact
  "Check downloading an archive."
  (let [url "https://github.com/VertNet/dwca-reader-clj/blob/develop/dev/archive-occ.zip?raw=true"
        temp-dir (Files/createTempDir)
        path (str (.getAbsolutePath temp-dir) "/archive-occ.zip")
        dir (io/file path)]
    (.exists (io/file path)) => false
    (download url path)
    (.exists (io/file path)) => true))

(fact
  "Check unzipping an archive."
  (let [file (.getPath (io/resource "archive-occ.zip"))
        dir (Files/createTempDir)
        path (.getAbsolutePath dir)]
    (unzip file path)
    (and (.exists (io/file path))
         (.exists (io/file (str path "/archive-occ/DarwinCore.txt")))
         (.exists (io/file (str path "/archive-occ/eml.xml")))
         (.exists (io/file (str path "/archive-occ/meta.xml")))) => true))

(fact
  "Check getting records from an archive."
  (let [dir (.getPath (io/resource "archive-occ"))]
    (count (get-records dir)) => 1534))

(fact
  "Check opening archive."
  (let [url "http://vertnet.nhm.ku.edu:8080/ipt/archive.do?r=ttrs_mammals"]
    (count (open url)) => 968))

(fact
  "Check number of fields."
  (let [url "http://vertnet.nhm.ku.edu:8080/ipt/archive.do?r=ttrs_mammals"
        records (open url)]
    (for [rec records]
      (count (field-vals rec)) => 182)
    (for [rec records]
      (count (field-keys rec)) => 182)))
