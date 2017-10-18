import React, {Component} from 'react';
import {
    StyleSheet,
    View,
    Text,
    Image,
    TextInput,
    Button
} from 'react-native';

import Util from '../../../common/Util';

export default class Login extends Component {
    constructor() {
        super();
        this.state = {}
    }
    onPress(){

    }
    render() {
        return (
            <View style={styles.container}>
                <Image style={styles.bgImage} source={require('../../../image/w3.png')}/>
                <View style={styles.loginInfo}>
                    <View style={styles.userInfos}>
                        <View style={styles.userImgBox}>
                            <Image
                                style={styles.userImg}
                                source={require('../../../image/20160531115534529.png')}/>
                        </View>
                        <View style={styles.userInput}>
                           
                            <TextInput style={styles.textInput} placeholder='用户名'></TextInput>
                        </View>
                        <View style={styles.userInput}>
                           
                            <TextInput style={styles.textInput} placeholder='密码' secureTextEntry={true} ></TextInput>
                        </View>
                        <View style={styles.button}>
                            <Button
                                title="登陆"
                                color="#0099cc"
                                onPress={(e)=>this.onPress()}
                            />
                        </View>
                    </View>
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        width: Util.size.width,
        height: Util.size.height
    },
    bgImage: {
        width: Util.size.width,
        height: Util.size.height
    },
    loginInfo: {
        width: Util.size.width,
        height: Util.size.height,
        position: 'absolute',
        top: 0,
        left: 0,
        alignItems: 'center',
        justifyContent: 'center'
    },
    userInfos: {
        flexDirection: 'column',
        borderRadius: 10,
        backgroundColor: 'rgba(255,255,255,0.4)',
        padding: 15,
        width: Util.size.width *0.7
    },
    userImgBox: {
        alignItems: 'center',
        justifyContent: 'center'
    },
    userImg: {
        height: 50,
        width: 50,
        marginTop: 25,
        marginBottom: 15
    },
    userInput: {
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
        
    },
    userLable: {
        width: 60
    },
    textInput: {
        flex: 1,
        height:30,
        paddingLeft:5,
        marginTop:10
    },
    button:{
        paddingTop:20,
        paddingBottom:10
    }
});
