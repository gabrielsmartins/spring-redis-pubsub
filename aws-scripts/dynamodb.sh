#!bin/bash

echo "########### Creating profile ###########"

aws configure set aws_access_key_id default_access_key --profile=localstack
aws configure set aws_secret_access_key default_secret_key --profile=localstack
aws configure set region sa-east-1 --profile=localstack

table="orders"

echo "########### Creating DynamoDB ${table} Table ###########"

aws dynamodb \
    create-table --table-name $table \
    --attribute-definitions \
        AttributeName=id,AttributeType=S \
    --key-schema \
        AttributeName=id,KeyType=HASH \
    --billing-mode PAY_PER_REQUEST \
    --endpoint http://localhost:4566 \
    --region sa-east-1 \
    --profile=localstack
    
echo "########### DynamoDB ${table} Table was created sucessfully ###########"

table="payments"

echo "########### Creating DynamoDB ${table} Table ###########"

aws dynamodb \
    create-table --table-name $table \
    --attribute-definitions \
        AttributeName=id,AttributeType=S \
    --key-schema \
        AttributeName=id,KeyType=HASH \
    --billing-mode PAY_PER_REQUEST \
    --endpoint http://localhost:4566 \
    --region sa-east-1 \
    --profile=localstack
    
echo "########### DynamoDB ${table} Table was created sucessfully ###########"
