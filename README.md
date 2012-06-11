# About

This project provides a Clojure wrapper around the [GBIF Darwin Core Archive Reader](http://code.google.com/p/darwincore/wiki/DarwinCoreArchiveReader) library.

# Usage

Fire up your REPL!

```bash
$ cd ~/Dropbox/github/vertnet/dwca-reader-clj
$ lein repl
```

Download, unzip, and iterate through Darwin Core records in an archive:

```clojure
(use `dwca.core)
(let [url "https://github.com/VertNet/dwca-reader-clj/blob/develop/dev/archive-occ.zip?raw=true"
      file "/home/eighty/archive-occ.zip"
      dir "/home/eighty"
      archive (str dir "/archive-occ")]
  (download url file) 
  (unzip file dir) 
  (for [record (get-records archive)]
    (println record)))
```