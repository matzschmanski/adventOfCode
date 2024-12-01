const path = require("path");
const fs = require("fs");
let map = [];
let mapPrep = [];
let longest =0;
const input = fs
  .readFileSync(path.join(__dirname, "input.txt"), "utf8")
  .toString()
  .split("\n")
  .forEach((line) => {
    if(line.length>longest){
      longest=line.length;
    }
    mapPrep.push(line);
  });

mapPrep.forEach(line => {
  let lineSplit = line.split('');
  if(lineSplit.length<longest){
    let filler = new Array(longest-lineSplit.length).fill(' ');
    lineSplit = lineSplit.concat(filler);
  }
  map.push(lineSplit);
});

const start = mapPrep[0].split('').filter(char => char === ' ').length-1;


module.exports = {
  map,start
};