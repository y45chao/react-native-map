import React, {Component} from 'react';

import {StyleSheet, View, Text, Image} from 'react-native';

import Util from '../../../common/Util';

export default class ImgView extends Component {
  constructor() {
    super();
    this.state = {}
  }

  render() {

    return (
      <View style={styles.container}>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-audio.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-chat.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-link.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-photo.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-audio.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-chat.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-link.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-photo.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-audio.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-chat.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-link.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-photo.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-audio.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-chat.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-link.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-photo.png')}
          resizeMode="contain"/>
          <Image
          style={styles.icon}
          source={require('../../../image/tumblr-audio.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-chat.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-link.png')}
          resizeMode="contain"/>
        <Image
          style={styles.icon}
          source={require('../../../image/tumblr-photo.png')}
          resizeMode="contain"/>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    flexWrap: 'wrap'
  },
  icon: {
    width: Util.size.width / 4,
    height: Util.size.width / 4
  }
});
