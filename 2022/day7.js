import { readFile } from '../util/util.js';
var rawInput = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2022/input/day7_1');
var startTime = performance.now()

var rawInput = rawInput.split(/\r?\n/);



var paths = new Map();


var currentFolder=''
rawInput.forEach(line =>  {
  // console.log('currentFolder',currentFolder);
    if(line.startsWith('$')){
      var lineSplit = line.split(' ');
      if(lineSplit[1]==='cd' && lineSplit[2] ==='..'){
        //up
        // console.log('currentFolder.lastIndexOf("/")',currentFolder.substring(0, currentFolder.lastIndexOf('/')));
        currentFolder = currentFolder.substring(0, currentFolder.lastIndexOf('/'));
        if(currentFolder===''){
          currentFolder='/';
        }
        
      }else if(lineSplit[1]==='cd' && lineSplit[2] !=='..'){
        if(currentFolder!=='/' && currentFolder!==''){
          currentFolder+='/';
        }if(lineSplit[2]!==''){
        currentFolder+=lineSplit[2];
        }
        // into
        paths.set(currentFolder,0);
        console.log(currentFolder, paths.get(currentFolder));
      }
  }else if(line.startsWith('dir ')){
    //nüscht
  }else {
    var lineSplit = line.split(' ');
    console.log(currentFolder, lineSplit[1], lineSplit[0]);
    if(paths.get(currentFolder)!=null){
    paths.set(currentFolder, paths.get(currentFolder)+ parseInt(lineSplit[0]));
    
    }else{
      console.log('?!#1')
    }
  }
  

});

for (let [key, value] of map) {
  console.log(key + " = " + value);
}

console.log(paths);

var endTime = performance.now()
console.log(`Call to doSomething took ${endTime - startTime} milliseconds`);


function addToParent(key,value, map){
  var parent = key.substring(key.lastIndexOf(''));
  if(map.get(parent)!=null){
    map.set(parent,map.get(parent)+value);
  }
}



