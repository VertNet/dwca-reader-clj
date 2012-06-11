(ns dwca.core
  "This namespace provides a Clojure API to the GBIF dwca-reader library."
  (:require [clojure.java.io :as io])
  (:import [org.gbif.file DownloadUtil]
           [org.gbif.dwc.record DarwinCoreRecord]
           [org.gbif.dwc.text ArchiveFactory]
           [java.io File]
           [java.net URL]
           [net.lingala.zip4j.core ZipFile]
           [net.lingala.zip4j.exception ZipException]))

(defn download
  "Downloads a Darwin Core Archive from the supplied URL to the supplied file."
  [url file]
  (DownloadUtil/download (URL. url) (File. file)))

(defn unzip
  "Unzips the supplied ZIP file into the supplied directory."
  [file dir]
  (let [zipfile (ZipFile. file)]
    (.extractAll zipfile dir)))

(defn get-records
  "Returns a sequence of DarwinCoreRecord objects from an archive directory."
  [dir]
  (let [archive (ArchiveFactory/openArchive (File. dir))]
    (iterator-seq (.iteratorDwc archive))))

