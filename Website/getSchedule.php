<?php
    $db = new SQLite3("db.db");
    $userID = $_GET["ID"];
    $pass = $_GET["pass"];

    $query = 'SELECT Password FROM Users WHERE Password == "'.$pass.'" AND ID == '.$userID;
    if($result = $db->query($query)->fetchArray())
    {
        // open schedule file
        $path = "./schedules/".$userID;
        $handle = fopen($path, "r");
        while (!feof($handle)) {
            $line = fgets($handle, filesize($path));

            // process the line read.
            $classData = explode(',', $line);
            $classTime = $classData[0];
            $classQuery = 'SELECT * FROM Class WHERE ID == "'.$classData[1].'"';
            if($classDB = $db->query($classQuery)->fetchArray())
            {
                $teacherQuery = "SELECT * FROM Users WHERE ID == ".$classDB["Teacher"];
                if($teacherDB = $db->query($teacherQuery)->fetchArray())
                {
                    echo "[".$classTime."] ".$classDB["Name"]." w/ ".$teacherDB["FirstName"]." ".$teacherDB["LastName"]."\n";
                }
            }
        }

        fclose($handle);
    }else{
        echo "false";
    }
?>