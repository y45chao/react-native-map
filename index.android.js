/**
 * Sample React Native App
 * android app
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';
//import App from "./jscode/features/demo/app"
import Util from './jscode/common/Util';
var RNEzMapView=require("./jscode/component/ezMap/RNEzMapView");

export default class JJApp extends Component {

  componentDidMount(){
    //var {NativeModules} =require('react-native') ;
    //NativeModules.RNEzMapInterface.InitMap();
  }
  render() {
    return (
      <View style={styles.container}>
        <Text>地图区域</Text>
        <RNEzMapView style={styles.mapView}></RNEzMapView>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FFF',
  },
  mapView:{
    width: Util.size.width,
    height: Util.size.height,
    borderWidth:2,
    borderColor:'#000',
    borderStyle:'solid'
  }
});

AppRegistry.registerComponent('JJApp', () => JJApp);
