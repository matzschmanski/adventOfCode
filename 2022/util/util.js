import readline from 'readline-sync';
import fs from 'fs';

export function readInput(isDebug) {
    var userinput = '';
    userinput = readline.question(`Input plox?!\n`);
    if(typeof isDebug !== "undefined") {
        console.log(`input has ${userinput.length} characters`);
    }
    return userinput;
}

export function readFile(path, isDebug){
    

const content =  fs.readFileSync(path, 'utf-8');
  if(typeof isDebug !== "undefined"){
    console.log(content);
  }
  return content;
}