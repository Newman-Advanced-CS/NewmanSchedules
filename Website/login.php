<?php
    $db = new SQLite3("db.db");
    $email = $_GET["email"];
    $pass = $_GET["pass"];

    $query = 'SELECT * FROM Users WHERE Email == "'.$email.'"';
    if($result = $db->query($query)->fetchArray())
    {
        if($result["Password"] == $pass)
        {
            echo $result["ID"];
            return;
        }
    }

    echo "false";
?>