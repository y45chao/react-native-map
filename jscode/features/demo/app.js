
import React, { Component } from 'react';
import {
  StyleSheet,
  View,
  Text,
  ScrollView
} from 'react-native';

import Login from './page/Login';

export default class App extends Component {
    constructor(){
        super();
        this.state={}
    }

  render() {
    let defaultName='Login';
    let defaultComponent=Login;
    return (
          <View style={styles.container}>
           <Login/>
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
