
import React, { Component } from 'react';
import {
  StyleSheet,
  View,
  Text,
  ScrollView
} from 'react-native';

import TopBar from './jscode/component/topBar/topBar';

export default class index extends Component {
    constructor(){
        super();
        this.state={}
    }

  render() {
    return (
          <View style={styles.container}>
            <TopBar></TopBar>
          </View>
    );
  }
}

const styles = StyleSheet.create({ 
  container: {
    flex: 1,
    flexDirection: 'column',
  }
});
