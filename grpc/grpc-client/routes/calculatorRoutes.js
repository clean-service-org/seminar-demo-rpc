const express = require('express');
const router = express.Router();
const calculatorController = require('../controllers/calculatorController');

router.get('/', calculatorController.getCalculatorPage);
router.post('/calculate', calculatorController.postCalculation);

module.exports = router;
