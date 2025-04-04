SELECT HRD.DEPT_ID
     , HRD.DEPT_NAME_EN
     , ROUND(AVG(HRE.SAL), 0) AS AVG_SAL
FROM HR_DEPARTMENT AS HRD
INNER JOIN HR_EMPLOYEES AS HRE
    ON HRE.DEPT_ID = HRD.DEPT_ID
GROUP BY HRD.DEPT_ID
       , HRD.DEPT_NAME_EN
ORDER BY AVG_SAL DESC;