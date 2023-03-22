# Weekly Meetings

## Table of Contents

- [October 17, 2022](#october-17-2022)
- [October 24, 2022](#october-24-2022)
- [November 03, 2022](#november-03-2022)
- [November 21, 2022](#november-21-2022)
- [December 06, 2022](#december-06-2022)
- [January 04, 2023](#january-04-2023)
- [January 09, 2023](#january-09-2023)
- [January 18, 2023](#january-18-2023)
- [January 31, 2023](#january-31-2023)
- [February 13, 2023](#february-13-2023)
- [February 20, 2023](#february-20-2023)
- [February 27, 2023](#february-27-2023)
- [March 6, 2023](#march-6-2023)
- [March 15, 2023](#march-15-2023)

---

## October 17, 2022

- Thesis objective
- First steps
- Introduction to QuickFaas
- `Paper Deadline:` around June

## October 24, 2022

- Talk about the next steps
- Paper structure that should be followed
- Overview and billing of FaaS providers:
  - [Google Cloud Platform](https://cloud.google.com/functions)
  - [AWS](https://aws.amazon.com/pt/lambda/)
  - [Azure](https://learn.microsoft.com/en-us/azure/azure-functions/functions-overview)

## November 03, 2022

- Talk about the work done:
  - Demo of Cloud Functions of GCP
  - Paper Status
- Next Steps:
  - Create FaaS with Azure Provider
  - Explore Function orchestrators

## November 21, 2022

- Azure Functions
- Orchestrators in Azure
  - The problem encountered
  - The API
  - Use cases
- Next Steps:
  - Complete the [sample](https://learn.microsoft.com/en-us/azure/azure-functions/durable/durable-functions-sequence?tabs=csharp)
  - Create a sample of orchestrators in GCP
  - Start document the function and orchestrators in the different provider (ideally also should be done in AWS)

## December 06, 2022

- Cloud Functions and his orchestration in Azure and GCP
- Some issues doing the AWS State machine
- Next Steps:
  - Creation of a Domain Specific Language (DSL)
  - Transpile my DSL for each specific cloud provider DSL
- Next meeting scheduled for [December 14, 2022](#december-14-2022)

## January 04, 2023

- Discussed some aspects about the DSL
  - Next Steps
  - Programming language
  - Translation of the DSL to the providers language
  - How providers do it
- Next Steps:
  - Creation of initial diagram
  - Creation of the model of my language

## January 09, 2023

- Talking about the model and diagrams
- Checking for some related work:
  - [OpenFaas](https://github.com/s8sg/faas-flow)
  - [ASPLOS'22 Session 7](https://www.youtube.com/watch?v=xCMx4J6Ur_c)

## January 18, 2023

- Talking about the next iteration on the paper
- Focusing in the chapter 2
- Scientific workflows are done by people who have some programming skills and want to use FaaS as tool to their work
- Check metrics on other papers to use in this project, such as deployment time of a workflow

## January 31, 2023

- What was done, so far
- Next objective is to focus on Related work

## February 13, 2023

- Focus on the Related Work
- Missing some explanations, about the difference of the frameworks with the project
- Paper structure

## February 20, 2023

- Complete paper introduction:
  - Architecture chapter
  - Workflow examples

## February 27, 2023

- Update the paper structure
  - Move patterns to chapter 2
  - Create Chapter 4 with a timeline
  - Make listing smaller
- Introduction:
  - Motivation for the work
  - Problem to solve
  - What was done in this area
  - Proposal for a solution
  - The structure of the document

## March 6, 2023

- Paper improvements and comments
- Brainstorming about the implementation of OmniFlow

## March 15, 2023

- Add new Billing account with 50$ credits
- Talking about the cloud client libraries for Workflows provided by each provider

## March 22, 2023

- Demonstration of using the GCP and AWS client libraries
- Alignment for the next steps and iteration for software 
- Last phase would be the creation of a GUI for the lib
