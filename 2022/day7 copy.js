import { readFile } from '../util/util.js';
var rawInput = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2022/input/day7_1');
var startTime = performance.now()

function addRecursive(paths){
// console.log('paths', paths);
  for (let [key, value] of paths) {
    // console.log('handling ', key);
    var parents = getAllParentPaths(key);

    parents.forEach(parent => {
      if(paths.get(parent)!=null){
   
        paths.set(parent,paths.get(parent)+value);
      }else{
        console.log('?!', parent);
        console.log(parent, paths.get(parent));
        // paths.set(parent,value);
      }
    });
    
    }
    // console.log('paths', paths);
  return paths;
}

function getAllParentPaths(path){
  
var parentPaths = [];
if(path === '/'){
  return parentPaths;
}
 while(path!=='/'&& path !==''){
  path = path.substring(0,path.lastIndexOf('/'));
  if(path ===''){
    parentPaths.push('/');
  }else{
  parentPaths.push(path);
  }
 }
 return parentPaths;
}
var rawInput = rawInput.split(/\r?\n/);



var paths = new Map();


var currentFolder=''
rawInput.forEach(line =>  {
  var parent = currentFolder;
  
  // console.log('currentFolder', currentFolder);
  if(line.startsWith('$ cd ')&& !line.startsWith('$ cd ..')){
    
    currentFolder += '/'+line.split(' ')[2];
    currentFolder =currentFolder.replace(/\/+/g, '/');
    if(currentFolder===''){
      currentFolder = '/';
      
    }
    
    if(paths.get(currentFolder)==null){
      paths.set(currentFolder.replace(/\/+/g, '/'), 0);
    }

  }else if(line.startsWith('$ cd ')&& line.startsWith('$ cd ..')){
    
    currentFolder = currentFolder.substring(0,currentFolder.indexOf('/'));
    currentFolder = currentFolder.replace(/\/+/g, '/');
    if(currentFolder===''){
      currentFolder = '/';
      
    }
    // console.log('currentFolder', currentFolder);
    
    //folder up
  }else if(line.startsWith('$ ls')){
    //list
  }else if(line.startsWith('dir ')){
    //ls dir
  }else {
    if(paths.get(currentFolder)){
      paths.set(currentFolder.replace(/\/+/g, '/'), paths.get(currentFolder)+parseInt(line.split(' ')[0]));
    }else{
    paths.set(currentFolder.replace(/\/+/g, '/'), parseInt(line.split(' ')[0]));
    }
  }

});


var sortedAsc = new Map([...paths].sort().reverse());


sortedAsc = addRecursive(sortedAsc);
  // console.log(paths);

var count =0;
var total = 0; 
for (let [key, value] of sortedAsc) {
  //console.log(key + " = " + value);
  if(value<=100000){
    // console.log('key', key);
    total +=value;
    count++;
  }
  
  }
  console.log('count',count);
  console.log('total',total);

var endTime = performance.now()
console.log(`Call to doSomething took ${endTime - startTime} milliseconds`);



