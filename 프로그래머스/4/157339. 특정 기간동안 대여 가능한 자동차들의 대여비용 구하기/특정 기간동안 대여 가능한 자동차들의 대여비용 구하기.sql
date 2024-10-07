-- 코드를 입력하세요
SELECT crcc.CAR_ID, crcc.CAR_TYPE, 30 * ROUND((DAILY_FEE * (100 - DISCOUNT_RATE) / 100)) AS 'FEE'
FROM CAR_RENTAL_COMPANY_CAR crcc, 
CAR_RENTAL_COMPANY_DISCOUNT_PLAN crcdp
WHERE
    crcc.CAR_TYPE = crcdp.CAR_TYPE
    AND
    crcc.CAR_ID NOT IN(
        SELECT crcc.CAR_ID 
        FROM CAR_RENTAL_COMPANY_CAR crcc,
        CAR_RENTAL_COMPANY_RENTAL_HISTORY crcrh
        WHERE 
            crcc.CAR_ID = crcrh.CAR_ID
            AND
            crcc.CAR_TYPE IN ('세단', 'SUV')
            AND
            (crcrh.START_DATE BETWEEN ('2022-11-01') AND ('2022-11-30')
            OR
            crcrh.END_DATE >= ('2022-11-01'))  
    )
    AND
    crcc.CAR_TYPE IN ('세단', 'SUV')
    AND
    crcdp.DURATION_TYPE = '30일 이상'
    AND
    30 * ROUND((DAILY_FEE * (100 - DISCOUNT_RATE) / 100)) BETWEEN 500000 AND 1999999
ORDER BY
    FEE DESC, crcc.CAR_TYPE ASC, CAR_ID DESC;