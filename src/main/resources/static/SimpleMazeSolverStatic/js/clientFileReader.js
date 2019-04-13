document.getElementById("clientSendFileBtn").disabled = true; 
var theMazeInputfromClientisValid = false;
var file;
var read;
function readSingleFile(evt) {
    //Retrieve the first (and only!) File from the FileList object
    file = evt.target.files[0];

    if(!file) {
        //alert("Failed to load file");
        document.getElementById("info").innerHTML = 
            "<span style='color: red'>" +
                "Failed to load file. Please select another file. Thank you!" +
            "</span>";
            return 0;
    }
    else if (!file.type.match('text/plain')) {
        //alert(file.name + " is not a valid text file.");
        document.getElementById("info").innerHTML = 
            "<span style='color: red'>'"
            + file.name + "' is not a valid text file. Please select another file. Thank you!"+
            "</span>";
            return 0;
    }
    else if (file) {
        read = new FileReader();

        read.readAsText(file); //reading operation, wich triggers onload

        //is triggered when reading operation is successfully completed.
        read.onload = function(e) { 
            var contents = e.target.result;
            if (extractDataFromFileContent(contents)==0 ) 
            {
                theMazeInputfromClientisValid = false;
                document.getElementById("clientSendFileBtn").disabled = true;
                return 0;
            }
            else 
            {
                document.getElementById("isSolveMaze").innerHTML = "";
                document.getElementById("msgMaze").innerHTML = "";
                document.getElementById("printMaze").innerHTML = "";
                document.getElementById("info").innerHTML = 
                    "<span style='color: green'>"
                    +"File '" + file.name + "' loaded and is ready to use. (Client-side)";+
                    "</span>";
                
                theMazeInputfromClientisValid = true;
                document.getElementById("clientSendFileBtn").disabled = false;
                return 1;
            }
        
            //console.log("ending");
            //   alert( "Got the file \n" 
            //         +"name: " + f.name + "\n"
            //         +"type: " + f.type + "\n"
            //         +"size: " + f.size + " bytes\n"
            //         + "starts with: " + contents.substr(0, 6) //contents.substr(0, contents.indexOf("t"))
            //   );  
        }

        //console.log("beggining");
    } 
}

//Maze from Client side input
var theMaze = [];

// Coordinates of start end in the maze
var xs;
var ys;
var xe;
var xe;
xs = ys = xe = ye = -1;

// rows and columns of maze
var MHeight;
var MWidth;
MHeight = MWidth = 0;

//helpers
var content_row = [];
var part_content_row = [];
var tempRow = [];

function extractDataFromFileContent(content){
    //console.log(content);
    content_row = content.split("\n");
    
    if(checkValidationContent()==0) return 0;
    
    part_content_row = content_row[0].split(" ");
    MWidth = parseInt(Number(part_content_row[0]));
    MHeight = parseInt(Number(part_content_row[1]));

    part_content_row = content_row[1].split(" ");
    ys = parseInt(Number(part_content_row[0]));
    xs = parseInt(Number(part_content_row[1]));

    part_content_row = content_row[2].split(" ");
    ye = parseInt(Number(part_content_row[0]));
    xe = parseInt(Number(part_content_row[1]));
    
    theMaze = [];
    for(var i = 3; i < content_row.length ; i++){
        tempRow = [];
        for(var j = 0; j < content_row[i].length ; j+=2){
            tempRow.push(content_row[i][j]);
        }
        if(content_row[i].length > 1)
            theMaze.push(tempRow);
    }
    
    //control the correctness of dimensions
    if( theMaze.length != MHeight ){
        document.getElementById("info").innerHTML = 
            "<span style='color: red'>'"
            + file.name + "' has not a valid internal content. Is expected the height input to be equal with the maze height. Please see the examples from server's corpus."+
            "</span>";
            return 0;
    }
    for(var i = 0; i < theMaze.length; i++){
        if ( theMaze[i].length != MWidth )
        {
            document.getElementById("info").innerHTML = 
                "<span style='color: red'>'"
                + file.name + "' has not a valid internal content. Is expected the width input to be equal with every maze's row width. Please see the examples from server's corpus."+
                "</span>";
                return 0;

        }
    }
    
    //control the correctness of start end point to be inside the borders of the maze
    if( (xs < 0 || xs > MHeight-1) || (ys < 0 || ys > MWidth-1) ||
        (xe < 0 || xe > MHeight-1) || (ye < 0 || ye > MWidth-1)  ){

        document.getElementById("info").innerHTML = 
            "<span style='color: red'>'"
            + file.name + "' has not a valid internal content. The start and end point must be inside maze's border. Please see the examples from server's corpus."+
            "</span>";
            return 0;

    }

    //console.log(theMaze.length);
    //console.log(theMaze);
}

function checkValidationContent(){

    for(var i = 0; i < content_row.length; i++)
    {            
        if(i<3){
            part_content_row = content_row[i].split(" ");

            if( !(part_content_row.length == 2) ) {
                document.getElementById("info").innerHTML = 
                "<span style='color: red'>'"
                + file.name + "' has not a valid internal content. Is expected to be two elements from each three first rows. Please see the examples from server's corpus."+
                "</span>";
                return 0;
            }

            for(var l = 0; l < part_content_row.length; l++)
            {
                if (isNaN(parseInt(Number(part_content_row[l])))){
                    document.getElementById("info").innerHTML = 
                    "<span style='color: red'>'"
                    + file.name + "' has not a valid internal content. Is expected to have Integer numbers. Please see the examples from server's corpus."+
                    "</span>";
                    return 0;
                }
            }
        }
        else{
            for(var j = 0; j < content_row[i].length; j+=2){

                //console.log(i+"-"+j+" : "+content_row[i][j] + " =)Content_row length: "+content_row[i].length );

                if(content_row[i][j] != "1" && content_row[i][j] != "0"){
                    document.getElementById("info").innerHTML = 
                    "<span style='color: red'>'"
                    + file.name + "' has not a valid internal content. Is expected to have '0' or '1' numbers wich are representing the maze. Please see the examples from server's corpus."+
                    "</span>";
                    
                    return 0;
                }
            }
        }
        
        
    }
}

function resetInputFile(){
    document.getElementById("fileinput").value = "";
    document.getElementById("clientSendFileBtn").disabled = true;
    document.getElementById("info").innerHTML = "";
}

document.getElementById('fileinput').addEventListener("change", readSingleFile, false);
document.getElementById('fileinput').addEventListener("click", resetInputFile, false);
