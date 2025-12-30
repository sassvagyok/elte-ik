<?php
include_once("storage.php");

class PointStorage extends Storage {
    public function __construct() {
        parent::__construct(new JsonIO("points.json"));
    }
}