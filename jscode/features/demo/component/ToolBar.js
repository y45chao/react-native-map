import React, {Component} from 'react';
import {
  StyleSheet,
  View,
  Text,
  ScrollView,
  Image,
  Button,
  Alert
} from 'react-native';

import Icon from 'react-native-vector-icons/MaterialCommunityIcons';

import ToolbarAndroid from "ToolbarAndroid";

export default class toolbar extends Component {
  constructor() {
    super();
    this.state = {}
  }
  onActionSelected(position) {
    let title = conToolBar[position].title;
    if (title === "settings") {
      Alert.alert("settings");
    } else if (title === "search") {
      Alert.alert("search");
    } else if (title === "user") {
      Alert.alert("user");
    }
  }

  onIconClicked() {
    Alert.alert("Icon Clicked");
  }

  render() {

    return (<Icon.ToolbarAndroid
      style={styles.toolBar}
      title="首页"
      titleColor="white"
      navIconName="chevron-left"
      actions={conToolBar}
      overflowIconName="apps"
      onActionSelected={this.onActionSelected}
      onIconClicked={this.onIconClicked}/>);
  }
}

const conToolBar = [
  {
    title: 'search',
    iconName: 'magnify',
    iconSize: 30,
    show: 'always',
    showWithText: true
  }, {
    title: 'settings',
    iconName: 'settings',
    iconSize: 30,
    show: 'always'
  }, {
    title: 'user',
    iconName: 'account',
    iconColor: "#FFF",
    show: 'ifRoom'
  }
];

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column'
  },
  toolBar: {
    height: 56,
    backgroundColor: '#3399CC'
  }
});
