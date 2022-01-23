<?php
    $db = new SQLite3("db.db");
    $userID = $_GET["ID"];
    $pass = $_GET["pass"];

    $query = 'SELECT Password FROM Users WHERE Password == "'.$pass.'" AND ID == '.$userID;
    if($result = $db->query($query)->fetchArray())
    {
        // open schedule file
        $handle = fopen($userID, "r");
        if ($handle) {
            while (($line = fgets($handle)) !== false) {
                // process the line read.
                $classData = split(",", $line);
                $classTime = $classData[0];
                $classQuery = 'SELECT * FROM Class WHERE ID == "'.$classData[1].'"';
                $classDB = $db->query($classQuery)->fetchArray();
                $teacherQuery = 'SELECT * FROM Users WHERE ID =='.$classDB["Teacher"];
                $teacherDB = $db->query($teacherQuery)->fetchArray();
                echo "[".$classTime."] ".$classDB["Name"]." w/ ".$teacherDB["FirstName"]." ".$teacherDB["LastName"];
            }

            fclose($handle);
        }
    }

    echo "false";
?>