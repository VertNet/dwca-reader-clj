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
(let [url "http://vertnet.nhm.ku.edu:8080/ipt/archive.do?r=ttrs_mammals"]
  (for [record (open url)]
    (field-vals record)))
```