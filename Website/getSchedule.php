<?php
    $db = new SQLite3("db.db");
    $userID = $_GET["ID"];
    $pass = $_GET["pass"];

    $query = 'SELECT Password FROM Users WHERE Password == "'.$pass.'" AND ID == '.$userID;
    if($result = $db->query($query)->fetchArray())
    {
        // open schedule file
        $path = "./schedules/".$userID.".txt";
        $handle = fopen($path, "r");
        while (! feof($handle)) {
            $line = fgets($handle, filesize($path));

            // process the line read.
            $classData = explode(',', $line);
            $classTime = $classData[0];
            $classQuery = 'SELECT * FROM Class WHERE ID == "'.trim($classData[1]).'"';
            if($classDB = $db->query($classQuery)->fetchArray())
            {
                $teacherQuery = "SELECT * FROM Users WHERE ID == ".$classDB["Teacher"];
                if($teacherDB = $db->query($teacherQuery)->fetchArray())
                {
                    echo "<strong>[".$classTime."]</strong> ".$classDB["Name"]." w/ ".$teacherDB["FirstName"]." ".$teacherDB["LastName"]."<br>";
                }
            }
        }

        fclose($handle);
    }else{
        echo "false";
    }
?>