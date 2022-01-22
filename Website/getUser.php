<?php
    $db = new SQLite3("db.db");
    $userID = $_GET["ID"];
    $pass = $_GET["pass"];

    $query = "SELECT * FROM Users WHERE ID == ".$userID;
    $result = $db->query($query)->fetchArray();

    if($result["Password"] == $pass)
    {
        echo "First:".$result["FirstName"].";Last:".$result["LastName"].";Year:".$result["Year"].";Type:".$result["Type"];
    }else{
        echo "Incorrect Password";
    }
?>