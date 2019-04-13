
var textNameFile="";
var printTheMazeSolution="";

function sendSelectedTextMazeFileLocatedOnServer(elementId) {

    var selectedFile = document.getElementById(elementId);

    // if (elt.selectedIndex == -1)
    //     return null;

    textNameFile = selectedFile.options[selectedFile.selectedIndex].text;
    //console.log(textNameFile);

    var objJsonSendtoServer = {
        nameMazeTextFile:textNameFile
    };

    $.ajax({
        type:"POST",
        url:"solveMaze",
        headers:{
            "Content-Type":"application/json",
            "Accept":"application/json"
        },
        data:JSON.stringify(objJsonSendtoServer),
        success:function(data){ //JSON.parse(data)
            //console.log("FROM SERVER "+ data.theSolvedMaze);
            printResultInDom(data);
        },
        error:function(er){
            console.log("Server Error :"+ er);
        }
    });

}

var mazelocatedonclient= false;
function sendSelectedTextMazeFileLocatedOnClient() {
    mazelocatedonclient = true
    var objJsonSendtoServer = {
        xs:xs,
        ys:ys,
        xe:xe,
        ye:ye,
        theMaze:theMaze
    };

    $.ajax({
        type:"POST",
        url:"solveMazeClient",
        headers:{
            "Content-Type":"application/json",
            "Accept":"application/json"
        },
        data:JSON.stringify(objJsonSendtoServer),
        success:function(data){ //JSON.parse(data)
            //console.log("FROM SERVER "+ data);
            printResultInDom(data);
            mazelocatedonclient = false;
        },
        error:function(er){
            console.log("Server Error :"+ er);
        }
    });

}

function printResultInDom(data){

    printTheMazeSolution="";

    if (!mazelocatedonclient)
    {
        document.getElementById("info").innerHTML = "File: '" + "<span style='color:green;'>"+ textNameFile + "'</span>";
    }
    document.getElementById("isSolveMaze").innerHTML = "Maze solved: " + data.solved; 
    document.getElementById("msgMaze").innerHTML = data.msg;

    if(data.solved == false) { 
        document.getElementById("printMaze").innerHTML = "null";
        return 0;
    }

    for(var i = 0; i < data.theSolvedMaze.length; i++){

        for(var j = 0; j < data.theSolvedMaze[i].length; j++){
            //&nbsp is space charachter for the browser
            if(data.theSolvedMaze[i][j] == ' ') {printTheMazeSolution += '&nbsp';}
            else if(data.theSolvedMaze[i][j] == 'X') {printTheMazeSolution += "<span style='color:green;'>X</span>";}
            else if(data.theSolvedMaze[i][j] == 'S') {printTheMazeSolution += "<span style='color:blue;'>S</span>";}
            else if(data.theSolvedMaze[i][j] == 'E') {printTheMazeSolution += "<span style='color:blue;'>E</span>";}
            else {printTheMazeSolution += data.theSolvedMaze[i][j]; }
        }
        printTheMazeSolution += "<br/>";
    }

    document.getElementById("printMaze").innerHTML = printTheMazeSolution;
    
}