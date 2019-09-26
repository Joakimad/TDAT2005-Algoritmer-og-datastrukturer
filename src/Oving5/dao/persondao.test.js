var mysql = require("mysql");

const PersonDao = require("./persondao.js");
const runsqlfile = require("./runsqlfile.js");

// GitLab CI Pool
var pool = mysql.createPool({
  connectionLimit: 1,
  host: "mysql.stud.iie.ntnu.no",
  user: "jonbergq",
  password: "hjAjtAlK",
  database: "jonbergq",
  debug: false,
  multipleStatements: true
});

let personDao = new PersonDao(pool);

beforeAll(done => {
  runsqlfile("dao/create_tables.sql", pool, () => {
    runsqlfile("dao/create_testdata.sql", pool, done);
  });
});

afterAll(() => {
  pool.end();
});

test("get one person from db", done => {
  function callback(status, data) {
    console.log(
      "Test callback: status=" + status + ", data=" + JSON.stringify(data)
    );
    expect(data.length).toBe(1);
    expect(data[0].navn).toBe("Hei Heisen");
    done();
  }

  personDao.getOne(2, callback);
});

test("get unknown person from db", done => {
  function callback(status, data) {
    console.log(
      "Test callback: status=" + status + ", data=" + JSON.stringify(data)
    );
    expect(data.length).toBe(0);
    done();
  }

  personDao.getOne(0, callback);
});

test("add person to db", done => {
  function callback(status, data) {
    console.log(
      "Test callback: status=" + status + ", data=" + JSON.stringify(data)
    );
    expect(data.affectedRows).toBeGreaterThanOrEqual(1);
    done();
  }

  personDao.createOne({
      navn: "Nils Nilsen",
      alder: 34,
      adresse: "Gata 3"
    },
    callback
  );
});

test("get all persons from db", done => {
  function callback(status, data) {
    console.log(
      "Test callback: status=" + status + ", data.length=" + data.length
    );
    expect(data.length).toBeGreaterThanOrEqual(2);
    done();
  }

  personDao.getAll(callback);
});

test("delete one persom from db", done => {
  function callback(status, data) {
    console.log("Test callback: status=" + status + ", data.length=" + data.length);
    expect(data.affectedRows).toBe(1);
    done();
  }
  personDao.deleteOne({
    id: 1
  }, callback);
});

test("update one person from  db", done => {
  function callback(status, data) {
    console.log("Test callback: status" + status + ", data.length=" + data.length);
    expect(data.affectedRows).toBe(1);
    done();
  }
  personDao.updateOne({
    navn: "Jon Bergquist",
    adresse: "Tullegata 123",
    alder: 29,
    id: 2
  }, callback);
  //
});