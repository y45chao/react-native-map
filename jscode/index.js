
import React, { Component } from 'react';
import {
  StyleSheet,
  View,
  Text,
  ScrollView,
  Image,
  Button
} from 'react-native';

import Icon from 'react-native-vector-icons/MaterialCommunityIcons';

import ToolbarAndroid from "ToolbarAndroid";
export default class index extends Component {
    constructor(){
        super();
        this.state={
            listShow:1
        }
    }
    BtnClick(){
        let {listShow}=this.state;
        let status=1;
        if(listShow===1){
            status=2;
        }
        this.setState({
            listShow:status
        })
    }
    onActionSelected(option){
        if(option.indexOf("setting")){
            
        }
    }
  render() {
      let {listShow}=this.state;
      let listStyle={};
        if(listShow===2){
            listStyle=styles.hide;
        }
        const conToolBar=[
            {
                title:"查询",
                icon:require("./image/search.png"),
                show:'always',
                showWithText:false
            },
            {
                title:"设置",
                icon:require("./image/setting.png"),
                show:'always',
                showWithText:false
            },
            {
                title:"列表",
                icon:require("./image/list.png"),
                show:'always',
                showWithText:false
            }
            
        ]
    
    return (
      <View style={styles.container}>
          {/* <ToolbarAndroid style={styles.toolBar}
            navIcon={require('./image/Android_Logo.png')} 
            actions={conToolBar}
            title="江西省南昌市"
            titleColor='#FFF'
          >
          </ToolbarAndroid> */}

          <Icon.ToolbarAndroid
                style={styles.toolBar} 
                title="首页"
                titleColor="white"
                navIconName="chevron-left"
                actions={[
                    { title: 'search', iconName: 'magnify', iconSize: 30, show: 'always',showWithText:true },
                    { title: 'Settings', iconName: 'settings', iconSize: 30, show: 'always' },
                    { title: 'user', iconName: 'account', iconColor: "#FFF", show: 'ifRoom' }
                ]}
                overflowIconName="apps"
                onActionSelected={this.onActionSelected}
          />
          <ScrollView>
              <View >
              <Image source={require('./image/20160531115534529.png')}></Image>
              </View>
            
          </ScrollView>
          <Button
            onPress={this.BtnClick.bind(this)}
            title="Press Me"
            accessibilityLabel="See an informative alert"
            />
      </View>
    );
  }
}



const styles = StyleSheet.create({ 
  container: {
    flex: 1,
    flexDirection: 'column',
  },
  toolBar:{
    height:56,
    backgroundColor: '#3399CC',
  },
  hide:{
      display:'none'
  }
});
