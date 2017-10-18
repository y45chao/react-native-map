var {NativeModules} =require('react-native') ;

function ezMapModules(){
    return NativeModules.RNEzMapInterface;
}

export function initEzMap(){
   return ezMapModules().InitMap();
}