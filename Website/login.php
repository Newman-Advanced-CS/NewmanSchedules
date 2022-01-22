<?php
    $db = new SQLiteDatabase("db.db");
    $email = $_GET["email"];
    $pass = $_GET["pass"];

    $query = "SELECT * FROM Users WHERE Email == ".$email;
    $result = $db->query($query)->fetchArray();

    if($result["Password"] == $pass)
    {
        echo $result["ID"];
    }else{
        echo "false";
    }
?>