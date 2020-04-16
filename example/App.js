import React, {useEffect, useState} from 'react';
import {
  FlatList,
  Text,
  View,
  StyleSheet,
  PermissionsAndroid,
  Platform
} from 'react-native';
import Dp3t from 'react-native-dp3t';
import {Button, Badge, Colors} from 'react-native-ui-lib';

const App: () => React$Node = () => {

  const [scanningStatus, setScanningStatus] = useState(false);
  const [advertisingStatus, setAdvertisingStatus] = useState(false);
  const [handshakes, setHandshakes] = useState([]);

  useEffect(() => {
    _getStatus();
  }, []);

  function _star_updateStatus() {
    setTimeout(() => {
      _getStatus();
    }, 500);
  }

  function _startScan() {
    Dp3t.startScan();
    _star_updateStatus();
  }

  function _startAdvertise() {
    Dp3t.startAdvertising();
    _star_updateStatus();
  }

  function _stoptAll() {
    Dp3t.stop();
    _star_updateStatus();
  }

  function _sync() {
    Dp3t.sync();
  }

  function _getHandshakes() {
    Dp3t.getHandshakes((handshakes) => {
      setHandshakes(handshakes)
    });
  }

  function _getStatus() {
    Dp3t.getStatus((status) => {
      setScanningStatus(status.scanning);
      setAdvertisingStatus(status.advertising);
    });
  }

  function _clearData() {
    Dp3t.clearData();
  }

  // request location permission (only for Android)
  async function _requestLocationPermission() {
    try {
      const granted = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.ACCESS_COARSE_LOCATION
      );
      if (granted === PermissionsAndroid.RESULTS.GRANTED) {
        alert("Location Permission Granted");
      } else {
        alert("Location Permission Denied");
      }
    } catch (err) {
      console.warn(err);
    }
  }


  return (
    <View style={styles.container}>
      <View style={styles.subContainer}>
        {_statusBadge('Scanning', scanningStatus.toString() == 'true')}
        {_statusBadge('Advertising', advertisingStatus.toString() == 'true')}
        {_renderPermissionButton()}
      </View>
      <View style={styles.subContainer}>
        {_renderButton('Start Scan', _startScan)}
        {_renderButton('Start Advertise', _startAdvertise)}
        {_renderButton('Stop All', _stoptAll)}
      </View>
      <View style={styles.subContainer}>
        {_renderButton('Sync', _sync)}
        {_renderButton('Get Status', _getStatus)}
      </View>
      <View style={styles.subContainer}>
        {_renderButton('Get Handshakes', _getHandshakes)}
        {_renderButton('Clear Data', _clearData)}
      </View>

      <FlatList
        data={handshakes}
        style={{marginTop: 5}}
        keyExtractor={item => item.hs_timestamp.toString()}
        renderItem={({item}) => <Text style={styles.item}>
          {new Date(item.hs_timestamp).toLocaleTimeString("en-US")} :
          {item.public_key} :
          {item.rssi} </Text>}
      />
    </View>
  );

  function _renderButton(text, onClick) {
    return (
      <Button
        backgroundColor={Colors.purple30}
        label={text}
        size='small'
        borderRadius={0}
        labelStyle={{fontWeight: '600'}}
        style={{marginBottom: 20, marginHorizontal: 10}}
        enableShadow
        onPress={onClick}
      />
    );
  }


  function _statusBadge(statusName, isOn) {
    return (
      <View style={styles.statusContainer}>
        <Text>{statusName}</Text>
        <Badge
          style={{marginHorizontal: 10}}
          size="small"
          backgroundColor={isOn == true ? Colors.green30 : Colors.red30}
        />
      </View>
    );
  }

  function _renderPermissionButton() {
    if (Platform.OS === 'android')
      return (
        _renderButton('Location Permission', _requestLocationPermission)
      );
    return null;
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
