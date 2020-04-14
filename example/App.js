/**
 * Sample React Native App
 *
 * adapted from App.js generated by the following command:
 *
 * react-native init example
 *
 * https://github.com/facebook/react-native
 */


import React, {useEffect, useState} from 'react';
import {
    TouchableOpacity,
    Text,
    View,
    StyleSheet,
} from 'react-native';
import Dp3t from 'react-native-dp3t';


const App: () => React$Node = () => {


    useEffect(() => {

    }, []);


    function _startScan() {
        Dp3t.start()
    }

    function _stoptScan() {
        Dp3t.stop()
    }

    function _sync() {
        Dp3t.sync()
    }

    function _getStatus() {
        Dp3t.getStatus()
    }

    function _clearData() {
        Dp3t.clearData()
    }


    return (
        <View style={styles.container}>
            <View style={styles.subContainer}>
                {_renderButton('Start Scan', _startScan)}
                {_renderButton('Stop Scan', _stoptScan)}
            </View>
            <View style={styles.subContainer}>
                {_renderButton('Sync', _sync)}
                {_renderButton('Get Status', _getStatus)}
            </View>
            <View style={styles.subContainer}>
                {_renderButton('Clear Data', _clearData)}
            </View>

            {/*            <FlatList
                data={devices}
                style={{marginTop: 5}}
                keyExtractor={item => item.public_key}
                renderItem={({item}) => <Text style={styles.item}>
                    {item.public_key} :
                    {item.device_address} :
                    {item.device_rssi} </Text>}
            />*/}
        </View>
    );

    function _renderButton(text, onClick) {
        return (
            <TouchableOpacity style={styles.btn} onPress={onClick}>
                <Text>{text}</Text>
            </TouchableOpacity>
        );
    }
};


const styles = StyleSheet.create({
    container: {
        flex: 1,
        paddingTop: 25,
        marginHorizontal: 5,
    },
    statusContainer: {
        flex: 1,
        flexDirection: 'row',
        alignItems: 'center'
    },
    subContainer: {
        flexWrap: 'wrap',
        flexDirection: 'row',
        alignItems: 'center'
    },
    btn: {
        marginHorizontal: 5,
        marginVertical: 10,
        padding: 10,
        alignItems: 'center',
        backgroundColor: 'orange'
    },
    item: {
        padding: 10,
        height: 44,
    },
});

export default App;
