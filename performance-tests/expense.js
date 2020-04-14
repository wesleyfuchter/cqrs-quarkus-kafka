import http from 'k6/http';
import {check, sleep} from 'k6';

export default function() {
  var url = 'http://localhost:8080/transactions';
  var payload = JSON.stringify({
	accountId: "wesley",
	description: "New Bike",
	type: "EXPENSE",
	value: 100
  });

  var params = {
	headers: {
	  'Content-Type': 'application/json',
	},
  };

  let res = http.post(url, payload, params);

  check(res, { 'success creating income': (r) => r.status === 201 });
  sleep(0.3);
}